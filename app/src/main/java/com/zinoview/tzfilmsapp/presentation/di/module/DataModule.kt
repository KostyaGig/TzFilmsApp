package com.zinoview.tzfilmsapp.presentation.di.module

import com.zinoview.tzfilmsapp.core.ResourceProvider
import com.zinoview.tzfilmsapp.data.DataFilms
import com.zinoview.tzfilmsapp.data.ExceptionMapper
import com.zinoview.tzfilmsapp.data.FilmsRepository
import com.zinoview.tzfilmsapp.data.MapperCloudToDataFilm
import com.zinoview.tzfilmsapp.data.cloud.CloudDataSource
import com.zinoview.tzfilmsapp.data.cloud.CloudFilm
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
class DataModule {

    @Provides
    fun provideFilmsRepository(
        cloudDataSource: CloudDataSource<List<CloudFilm>>,
        resourceProvider: ResourceProvider
    ) : FilmsRepository<DataFilms>  {
        return FilmsRepository.Base(
            cloudDataSource,
            MapperCloudToDataFilm.Base(),
            ExceptionMapper.Base(resourceProvider)
        )
    }
}