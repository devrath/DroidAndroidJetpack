package com.demo.code.paging.usingRemoteAndLocalSource.models

import com.demo.code.paging.usingRemoteSource.models.PostContainer

class PostsListing(val children: List<PostContainer>, val after: String?, val before: String?)