package com.andreivanceadev.sliidetechnical.converter

import com.andreivanceadev.sliidetechnical.api.model.CreateUserApiModel
import com.andreivanceadev.sliidetechnical.models.UserItem
import toothpick.InjectConstructor

@InjectConstructor
class UserApiModelConverter : ModelConverter<UserItem, CreateUserApiModel> {
    override fun convert(inData: UserItem): CreateUserApiModel {
        return CreateUserApiModel(
            inData.name,
            inData.gender,
            inData.email,
            inData.status
        )
    }
}