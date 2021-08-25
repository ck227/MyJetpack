package com.ck.ui

import com.ck.data.NewsBean


class NewsViewModel(newsBean: NewsBean) {

    private val imgUrlValue = checkNotNull(newsBean.informationImgApp)

    private val titleValue = newsBean.title

    private val sourceValue = newsBean.source

    private val timeValue = newsBean.saveTime

    val imgUrl get() = imgUrlValue

    val title get() = titleValue
    
    val source get() = sourceValue

    val time get() = timeValue

}