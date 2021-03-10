package com.andreivanceadev.sliidetechnical.converter

import com.andreivanceadev.sliidetechnical.api.model.UserApiModel
import com.andreivanceadev.sliidetechnical.models.UserItem
import com.andreivanceadev.sliidetechnical.rules.UsersRules
import toothpick.InjectConstructor

@InjectConstructor
class UsersConverter(private val rules: UsersRules) : ModelConverter<List<UserApiModel>, List<UserItem>> {
    override fun convert(inData: List<UserApiModel>): List<UserItem> =
        inData.map {
            UserItem(
                it.id,
                it.name,
                it.email,
                rules.getUserCreationTimeRelativeToNow(it.createdAt)
            )
        }
}