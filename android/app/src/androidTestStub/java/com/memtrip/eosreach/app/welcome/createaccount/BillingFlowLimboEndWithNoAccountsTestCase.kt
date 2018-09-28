package com.memtrip.eosreach.app.welcome.createaccount

import com.memtrip.eosreach.R
import com.memtrip.eosreach.StubTestCase
import com.memtrip.eosreach.api.StubApi
import com.memtrip.eosreach.api.stub.Stub
import com.memtrip.eosreach.api.stub.StubMatcher
import com.memtrip.eosreach.api.stub.request.BasicStubRequest
import com.memtrip.eosreach.api.stub.request.ErrorOnFirstStubRequest
import com.memtrip.eosreach.billing.BillingConnectionResponse
import com.memtrip.eosreach.billing.SkuStub

/**
 * When the billing flow succeeds, but the createAccount endpoint fails, we determine
 * whether the account was created by calling get_key_accounts with the current
 * session public key. If an error occurs fetching the accounts for public key, the
 * user is displayed a retry screen. They will be presented this screen until a valid
 * response is returned from get accounts for public key.
 *
 * In this test case no accounts are retrieved, at this point it is safe to assume that an
 * account was not created and an error is displayed.
 */
class BillingFlowLimboEndWithNoAccountsTestCase : StubTestCase() {

    override fun test() {
        splashRobot
            .navigateCreateAccount()
        createAccountRobot
            .verifyEnterAccountNameScreen()
            .typeAccountName()
            .selectCreateAccountButton()
            .verifyLimboScreen()
            .selectLimboSettingsButton()
        settingsRobot
            .verifySettingsScreen()
        commonRobot
            .pressBack()
            .pressBack()
        splashRobot
            .navigateCreateAccount()
        createAccountRobot
            .verifyLimboScreen()
            .selectLimboRetryButton()
            .verifyCreateAccountError()
    }

    override fun billingConnectionResponse(): BillingConnectionResponse {
        return BillingConnectionResponse.Success(
            skuDetails(SkuStub.SUCCESS)
        )
    }

    override fun stubApi(): StubApi = object : StubApi(context()) {

        private val getKeyAccountsRequest = ErrorOnFirstStubRequest(200, {
            readJsonFile("stub/happypath/happy_path_get_key_accounts_empty.json")
        })

        override fun createAccount(): Stub = Stub(
            StubMatcher(
                context.getString(R.string.app_default_utility_endpoint_root),
                Regex("createAccount$")
            ),
            BasicStubRequest(400)
        )

        override fun getKeyAccounts(): Stub = Stub(
            StubMatcher(
                context.getString(R.string.app_default_eos_endpoint_root),
                Regex("v1/history/get_key_accounts$")
            ),
            getKeyAccountsRequest
        )
    }
}