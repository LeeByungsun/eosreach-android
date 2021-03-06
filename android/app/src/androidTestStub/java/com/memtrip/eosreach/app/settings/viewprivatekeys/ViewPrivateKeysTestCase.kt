package com.memtrip.eosreach.app.settings.viewprivatekeys

import com.memtrip.eos.core.crypto.EosPrivateKey
import com.memtrip.eosreach.StubTestCase
import com.memtrip.eosreach.api.StubApi

class ViewPrivateKeysTestCase : StubTestCase() {

    override fun test() {

        val privateKey = EosPrivateKey("5KQwrPbwdL6PhXujxW37FSSQZ1JiwsST4cqQzDeyXtP79zkvFD3")

        importKeyOrchestra.go(privateKey.toString())
        accountNavigationRobot
            .selectNavigationIcon()
            .selectSettingsNavigationItem()
        settingsRobot
            .verifySettingsScreen()
            .selectViewPrivateKeysSettingsItem()
            .verifyViewPrivateKeysScreen()
            .selectShowPrivateKeysButton()
        settingsRobot
            .verifyViewPrivateKeyScreenPrivateKeyValue(privateKey.toString())
            .verifyViewPrivateKeyScreenPublicKeyValue(privateKey.publicKey.toString())
            .verifyViewPrivateKeyScreenAccountsValue("memtripissue, memtripissu3")
    }

    override fun stubApi(): StubApi {
        return super.stubApi()
    }
}