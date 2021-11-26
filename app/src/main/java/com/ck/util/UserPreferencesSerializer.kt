package com.ck.util

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.ck.myjetpack.User
import java.io.InputStream
import java.io.OutputStream

object UserPreferencesSerializer : Serializer<User> {
    override val defaultValue: User = User.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): User {
        try {
            return User.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: User, output: OutputStream) = t.writeTo(output)
}

//val Context.userDataStore: DataStore<User> by dataStore(
//    fileName = "user.pb",
//    serializer = UserPreferencesSerializer
//)

