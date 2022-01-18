package tmidev.themeswitch.util

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import tmidev.themeswitch.domain.model.Post

object PostFactory {
    fun createPostList(size: Int) = List(size = size) { index ->
        createPost(index = index)
    }

    private fun createPost(index: Int): Post {
        val loremIpsum = LoremIpsum(
            words = (15..30).random()
        ).values.joinToString { it }
        return Post(
            id = index,
            title = "Title of post $index",
            post = "This is a auto-generated post. $loremIpsum"
        )
    }
}