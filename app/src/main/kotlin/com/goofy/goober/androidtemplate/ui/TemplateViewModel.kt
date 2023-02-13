package com.goofy.goober.androidtemplate.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TemplateViewModel @Inject constructor(
    private val getTemplateData: GetTemplateData
) : ViewModel() {
    val templateData get() = getTemplateData()
}
