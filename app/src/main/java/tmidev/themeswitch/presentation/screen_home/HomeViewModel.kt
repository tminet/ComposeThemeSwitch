package tmidev.themeswitch.presentation.screen_home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import tmidev.themeswitch.util.PostFactory
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val postFactory = PostFactory
    private val postListSize = (20..100).random()
    val postList = mutableStateOf(value = postFactory.createPostList(size = postListSize))
}