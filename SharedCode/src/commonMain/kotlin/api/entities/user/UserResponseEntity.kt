package com.farsidelabs.pacingforynab.api.entities.user

import kotlinx.serialization.Serializable

@Serializable
data class UserResponseEntity(
    val data: UserDataEntity
)

@Serializable
data class UserDataEntity(
    val user: UserEntity
)
