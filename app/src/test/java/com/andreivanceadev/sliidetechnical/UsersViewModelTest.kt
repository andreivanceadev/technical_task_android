package com.andreivanceadev.sliidetechnical

import android.app.Application
import com.andreivanceadev.sliidetechnical.api.UsersApiService
import com.andreivanceadev.sliidetechnical.models.LoadUsersEvent
import com.andreivanceadev.sliidetechnical.repository.TimeRepository
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.reactivex.rxjava3.core.Observable
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import toothpick.Toothpick
import toothpick.config.Module
import java.util.*

@ExtendWith(MockKExtension::class)
internal class UsersViewModelTest {

    @MockK
    lateinit var application: Application

    @MockK
    lateinit var usersApiService: UsersApiService

    @MockK
    lateinit var timeRepository: TimeRepository

    private val viewModel: UsersViewModel = UsersViewModel()

    @BeforeEach
    fun setup() {
        val testScope = Toothpick.openScope(application)
        testScope.installModules(
            object : Module() {
                init {
                    bind(UsersApiService::class.java).toInstance(usersApiService)
                    bind(TimeRepository::class.java).toInstance(timeRepository)
                }
            }
        )
        Toothpick.inject(viewModel, testScope)
    }

    @Test
    fun testGetUsersEvent() {
        //GIVEN
        every { timeRepository.getCurrentTimeMs() } returns Calendar.getInstance()
            .apply { set(2021, 3, 11) }.timeInMillis
        every { usersApiService.getUsers() } returns Observable.just(Result.success(TestData.getUsers()))

        val appStateObservableTest =
            viewModel.appStateObservable.test()

        //WHEN
        viewModel.sendEvent(LoadUsersEvent(application))

        //THEN
        appStateObservableTest.assertNoErrors()
        appStateObservableTest.assertValueCount(2)

        val values = appStateObservableTest.values()
        assert(values[0].loading)
        assert(!values[1].loading && values[1].users != null)

        appStateObservableTest.dispose()
    }


}