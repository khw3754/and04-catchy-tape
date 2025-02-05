package com.ohdodok.catchytape.core.domain.model

enum class CtErrorType(val errorCode: Int) {
    CONNECTION(40),
    IO(41),
    SSL_HAND_SHAKE(42),
    UN_KNOWN(43),
    UN_AUTHORIZED(401),

    DUPLICATED_NICKNAME(1001),
    NOT_EXIST_PLAYLIST_ON_USER(4001),
    NOT_EXIST_MUSIC(4002),
    ALREADY_ADDED(4003),
    INVALID_INPUT_VALUE(4004),
    NOT_EXIST_USER(4005),
    ALREADY_EXIST_EMAIL(4006),
    NOT_EXIST_GENRE(4007),
    INVALID_GREEN_EYE_REQUEST(4011),
    FAIL_GREEN_EYE_IMAGE_RECOGNITION(4012),
    BAD_IMAGE(4013),
    WRONG_TOKEN(4100),
    EXPIRED_TOKEN(4101),
    SERVER(5000),
    SERVICE(5001),
    ENCODING_FAILURE(5002);

    companion object {
        val ctErrorEnums = CtErrorType.values().toList()
    }
}


