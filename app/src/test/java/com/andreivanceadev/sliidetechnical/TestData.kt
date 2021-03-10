package com.andreivanceadev.sliidetechnical

import com.andreivanceadev.sliidetechnical.api.model.UserApiModel

object TestData {
    fun getUsers(): List<UserApiModel> {
        return listOf(
            UserApiModel(
                1,
                "TestName",
                "test-email",
                "2021-03-06T03:50:04.260+05:30"
            ),
            UserApiModel(
                2,
                "TestName",
                "test-email",
                "2021-03-02T03:50:04.260+05:30"
            )
        )
    }
}
