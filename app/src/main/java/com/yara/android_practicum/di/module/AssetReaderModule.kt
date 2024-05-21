package com.yara.android_practicum.di.module

import com.yara.android_practicum.data.model.CategorySerialized
import com.yara.android_practicum.data.model.EventSerialized
import com.yara.android_practicum.data.util.AssetReaderImpl
import com.yara.android_practicum.data.util.CategoryDeserializer
import com.yara.android_practicum.data.util.EventDeserializer
import com.yara.android_practicum.utils.AssetReader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AssetReaderModule {

    @Singleton
    @Provides
    fun provideCategoryAssetReader(): AssetReader<CategorySerialized> =
        AssetReaderImpl(CategoryDeserializer)

    @Singleton
    @Provides
    fun provideEventAssetReader(): AssetReader<EventSerialized> =
        AssetReaderImpl(EventDeserializer)
}