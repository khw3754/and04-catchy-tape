package com.ohdodok.catchytape.core.data.repository

import com.ohdodok.catchytape.core.data.api.PlaylistApi
import com.ohdodok.catchytape.core.data.model.PlaylistRequest
import com.ohdodok.catchytape.core.domain.model.Playlist
import com.ohdodok.catchytape.core.domain.repository.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class PlaylistRepositoryImpl @Inject constructor(
    private val playlistApi: PlaylistApi
) : PlaylistRepository {


    override fun getPlaylists(): Flow<List<Playlist>> = flow {
        val playlistResponse = playlistApi.getPlaylists()
        emit( playlistResponse.map { it.toDomain() })
    }


    override suspend fun postPlaylist(title: String) {
        playlistApi.postPlaylist(PlaylistRequest(title = title))
    }
}



