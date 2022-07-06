package com.jbavaji.core.data

data class User(
    var email: String,
    var password: String,
    var creationTime: Long,
    var updateTime: Long,
    var id: Long = 0
)
