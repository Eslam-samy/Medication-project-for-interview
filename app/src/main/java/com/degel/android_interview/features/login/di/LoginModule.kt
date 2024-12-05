package com.degel.android_interview.features.login.di

import com.degel.android_interview.features.login.data.local.UserDao
import com.degel.android_interview.features.login.data.repository.LoginRepositoryImpl
import com.degel.android_interview.features.login.domian.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.ktor.client.HttpClient


@Module
@InstallIn(ViewModelComponent::class)
object LoginModule {

    @Provides
    fun provideLoginRepository(httpClient: HttpClient, dao: UserDao): LoginRepository {
        return LoginRepositoryImpl(httpClient, dao)
    }

}