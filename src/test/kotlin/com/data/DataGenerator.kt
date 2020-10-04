package com.data


import com.api.Endpoints
import com.api.Requests
import com.api.Status
import com.models.request.register.RegisterModel
import com.models.response.register.PositiveRegisterModel
import com.models.response.users.ListUsersModel
import io.qameta.allure.Step
import kotlinx.serialization.json.Json
import org.json.JSONObject

/*** Генератор тестовых данных*/
open class DataGenerator : Requests(), RandomStringGenerator {

    @Step("Регистрация в системе нового пользователя")
    fun registerNewUser(login: String, password: String): PositiveRegisterModel {

        val usersJson: JSONObject = post(Endpoints.REGISTER.URL, RegisterModel(login, password).getBody(), Status.OK.code)
        return Json.decodeFromString(PositiveRegisterModel.serializer(), usersJson.toString())
    }

    @Step("Получить список всех пользователей с учетом пагинации")
    fun getUsersInPage(page: Int): ListUsersModel {
        val usersJson: JSONObject = get(Endpoints.USERS.URL + "?page=$page", Status.OK.code)
        return Json.decodeFromString(ListUsersModel.serializer(), usersJson.toString())
    }
}