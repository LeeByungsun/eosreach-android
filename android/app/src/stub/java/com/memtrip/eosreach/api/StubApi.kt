/*
Copyright (C) 2018-present memtrip

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.memtrip.eosreach.api

import android.content.Context
import com.memtrip.eosreach.R
import com.memtrip.eosreach.api.stub.Stub
import com.memtrip.eosreach.api.stub.StubMatcher
import com.memtrip.eosreach.api.stub.readFile
import com.memtrip.eosreach.api.stub.request.BasicStubRequest

import java.util.Arrays.asList

abstract class StubApi(
    val context: Context
) {

    open fun getInfo(): Stub = Stub(
        StubMatcher(
            context.getString(R.string.app_default_eos_endpoint_root),
            Regex("v1/chain/get_info$")
        ),
        BasicStubRequest(200, {
            readJsonFile("stub/happypath/happy_path_get_info.json")
        })
    )

    open fun getAccount(): Stub = Stub(
        StubMatcher(
            context.getString(R.string.app_default_eos_endpoint_root),
            Regex("v1/chain/get_account$")
        ),
        BasicStubRequest(200, {
            readJsonFile("stub/happypath/happy_path_get_account_unstaked.json")
        })
    )

    open fun getKeyAccounts(): Stub = Stub(
        StubMatcher(
            context.getString(R.string.app_default_eos_endpoint_root),
            Regex("v1/history/get_key_accounts$")
        ),
        BasicStubRequest(200, {
            readJsonFile("stub/happypath/happy_path_get_key_accounts.json")
        })
    )

    open fun getCurrencyBalance(): Stub = Stub(
        StubMatcher(
            context.getString(R.string.app_default_eos_endpoint_root),
            Regex("v1/chain/get_currency_balance$")
        ),
        BasicStubRequest(200, {
            readJsonFile("stub/happypath/happy_path_get_sys_currency_balance.json")
        })
    )

    open fun getCustomTokensTableRows(): Stub = Stub(
        StubMatcher(
            context.getString(R.string.app_default_eos_endpoint_root),
            Regex("v1/chain/get_table_rows$"),
            readJsonFile("stub/request/request_get_table_rows_customtoken.json")
        ),
        BasicStubRequest(200, {
            readJsonFile("stub/happypath/happy_path_get_table_rows_customtoken.json")
        })
    )

    open fun getTableRowsProducerJson(): Stub = Stub(
        StubMatcher(
            context.getString(R.string.app_default_eos_endpoint_root),
            Regex("v1/chain/get_table_rows$"),
            readJsonFile("stub/request/request_get_table_rows_producerjson.json")
        ),
        BasicStubRequest(200, {
            readJsonFile("stub/happypath/happy_path_get_table_rows_producerjson.json")
        })
    )

    open fun getTableRowsProducerSingleJson(): Stub = Stub(
        StubMatcher(
            context.getString(R.string.app_default_eos_endpoint_root),
            Regex("v1/chain/get_table_rows$"),
            readJsonFile("stub/request/request_get_table_rows_producerjson_single.json")
        ),
        BasicStubRequest(200, {
            readJsonFile("stub/happypath/happy_path_get_table_rows_producerjson_single.json")
        })
    )

    open fun getTableRowsProxyVoter(): Stub = Stub(
        StubMatcher(
            context.getString(R.string.app_default_eos_endpoint_root),
            Regex("v1/chain/get_table_rows$"),
            readJsonFile("stub/request/request_get_table_rows_regproxyinfo.json")
        ),
        BasicStubRequest(200, {
            readJsonFile("stub/happypath/happy_path_get_table_rows_regproxyinfo.json")
        })
    )

    open fun getTableRowsSingleProxyVoter(): Stub = Stub(
        StubMatcher(
            context.getString(R.string.app_default_eos_endpoint_root),
            Regex("v1/chain/get_table_rows$"),
            readJsonFile("stub/request/request_get_table_rows_regproxyinfo_single.json")
        ),
        BasicStubRequest(200, {
            readJsonFile("stub/happypath/happy_path_get_table_rows_regproxyinfo_single.json")
        })
    )

    open fun getTableRowsAllocatedBandwidth(): Stub = Stub(
        StubMatcher(
            context.getString(R.string.app_default_eos_endpoint_root),
            Regex("v1/chain/get_table_rows$"),
            readJsonFile("stub/request/request_get_table_rows_delegated_bandwidth.json")
        ),
        BasicStubRequest(200, {
            readJsonFile("stub/happypath/happy_path_get_table_rows_delegated_bandwidth.json")
        })
    )

    open fun getActions(): Stub = Stub(
        StubMatcher(
            context.getString(R.string.app_default_eos_endpoint_root),
            Regex("v1/history/get_actions$")
        ),
        BasicStubRequest(200, {
            readJsonFile("stub/happypath/happy_path_get_actions.json")
        })
    )

    open fun getBlockProducers(): Stub = Stub(
        StubMatcher(
            context.getString(R.string.app_default_eos_endpoint_root),
            Regex("v1/chain/get_producers$")
        ),
        BasicStubRequest(200, {
            readJsonFile("stub/happypath/happy_path_get_producers.json")
        })
    )

    open fun pushTransaction(): Stub = Stub(
        StubMatcher(
            context.getString(R.string.app_default_eos_endpoint_root),
            Regex("v1/chain/push_transaction$")
        ),
        BasicStubRequest(400, {
            readJsonFile("stub/error/error_push_transaction_log.json")
        })
    )

    open fun getPriceForCurrency(): Stub = Stub(
        StubMatcher(
            context.getString(R.string.app_default_utility_endpoint_root),
            Regex("price/(.*)$")
        ),
        BasicStubRequest(200, {
            readJsonFile("stub/happypath/happy_path_price.json")
        })
    )

    open fun createAccount(): Stub = Stub(
        StubMatcher(
            context.getString(R.string.app_default_utility_endpoint_root),
            Regex("createAccount$")
        ),
        BasicStubRequest(200, {
            readJsonFile("stub/happypath/happy_path_create_account.json")
        })
    )

    fun readJsonFile(fileName: String): String {
        return readFile(fileName, context)
    }

    fun stubs(): List<Stub> = asList(
        getInfo(),
        getAccount(),
        getKeyAccounts(),
        getCurrencyBalance(),
        getActions(),
        pushTransaction(),
        getPriceForCurrency(),
        createAccount(),
        getCustomTokensTableRows(),
        getTableRowsProducerJson(),
        getTableRowsProducerSingleJson(),
        getTableRowsProxyVoter(),
        getTableRowsSingleProxyVoter(),
        getTableRowsAllocatedBandwidth(),
        getBlockProducers()
    )
}