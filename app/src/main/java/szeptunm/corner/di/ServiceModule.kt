package szeptunm.corner.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import szeptunm.corner.BuildConfig
import szeptunm.corner.dataaccess.api.service.NewsService
import szeptunm.corner.dataaccess.api.service.StandingService
import szeptunm.corner.dataaccess.api.service.TeamService
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Named
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideGsonConverter(): Gson {
        return GsonBuilder()
                .create()
    }

    @Provides
    @Singleton
    @Named("news")
    fun createHttpClientNews(): OkHttpClient? {
        return OkHttpClient.Builder()
                .connectTimeout(20, SECONDS)
                .readTimeout(20, SECONDS)
                .build()
    }

    @Provides
    @Singleton
    @Named("team")
    fun createHttpClientTeam(): OkHttpClient? {
        return OkHttpClient.Builder()
                .connectTimeout(20, SECONDS)
                .readTimeout(20, SECONDS)
                .build()
    }

    @Provides
    @Singleton
    @Named("standing")
    fun createHttpClientStanding(): OkHttpClient? {
        return OkHttpClient.Builder()
                .connectTimeout(20, SECONDS)
                .readTimeout(20, SECONDS)
                .build()
    }

    @Provides
    @Singleton
    @Named("news")
    fun provideNewsRetrofit(gson: Gson, @Named("news") client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.NEWS_API_HTTP_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
    }

    @Provides
    @Singleton
    @Named("team")
    fun provideTeamRetrofit(gson: Gson, @Named("team") client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.TEAM_API_HTTP_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
    }

    @Provides
    @Singleton
    @Named("standing")
    fun provideStandingRetrofi(gson: Gson, @Named("standing") client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.STATISTIC_API_HTTP_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
    }

    @Provides
    @Singleton
    fun provideNewsService(@Named("news") retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }

    @Provides
    @Singleton
    fun provideTeamService(@Named("team") retrofit: Retrofit): TeamService {
        return retrofit.create(TeamService::class.java)
    }

    @Provides
    @Singleton
    fun provideStandingService(@Named("standing") retrofit: Retrofit): StandingService {
        return retrofit.create(StandingService::class.java)
    }
}