package com.ck.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.ck.data.LoginBean
import com.ck.myjetpack.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.userDataStore: DataStore<User> by dataStore(
    fileName = "user.pb",
    serializer = UserPreferencesSerializer
)

class UserRepository(context: Context) {

    private val hello = context

    val user: Flow<User> = context.userDataStore.data
        .map { user ->
            user
        }


    //更新头像
    suspend fun updateHeadImg(headImg: String) {
        hello.userDataStore.updateData { currentSettings ->
            currentSettings.toBuilder()
                .setHeadImg(headImg)
                .build()
        }
    }

    //更新昵称
    suspend fun updateNickName(nickName: String) {
        hello.userDataStore.updateData { currentSettings ->
            currentSettings.toBuilder()
                .setNickName(nickName)
                .build()
        }
    }

    //存数据
    suspend fun updateUser(loginBean: LoginBean) {
        hello.userDataStore.updateData { currentSettings ->
            currentSettings.toBuilder()
                .setAccountBalance(loginBean.accountBalance)
                .setAutograph(loginBean.autograph)
                .setHeadImg(loginBean.headImg)
                .setId(loginBean.id)
                .setLoginName(loginBean.loginName)
                .setMemberBalance(loginBean.memberBalance)
                .setNickName(loginBean.nickName)
                .setPointCount(loginBean.pointCount)
                .setSex(loginBean.sex)
                .setUserType(loginBean.userType)
                .setUserTypeId(loginBean.userTypeId)
                .build()
        }
    }

    suspend fun logout() {
        hello.userDataStore.updateData { it ->
            it.toBuilder().clear().build()
        }
    }

}