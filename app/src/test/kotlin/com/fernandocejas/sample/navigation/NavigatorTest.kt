package com.fernandocejas.sample.navigation

import com.fernandocejas.sample.AndroidTest
import com.fernandocejas.sample.features.login.Authenticator
import com.fernandocejas.sample.features.login.LoginActivity
import com.fernandocejas.sample.features.movies.MoviesActivity
import com.fernandocejas.sample.shouldNavigateTo
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify


class NavigatorTest : AndroidTest() {
    private lateinit var navigator: Navigator

    @Mock private lateinit var authenticator: Authenticator

    @Before fun setup() {
        navigator = Navigator(authenticator)
    }

    @Test fun forwardUserToLoginScreen() {
        whenever(authenticator.userLoggedIn()).thenReturn(false)

        navigator.showMain(context())

        verify(authenticator).userLoggedIn()
        RouteActivity::class shouldNavigateTo LoginActivity::class
    }

    @Test fun forwardUserToMoviesScreen() {
        whenever(authenticator.userLoggedIn()).thenReturn(true)

        navigator.showMain(context())

        verify(authenticator).userLoggedIn()
        RouteActivity::class shouldNavigateTo MoviesActivity::class
    }
}
