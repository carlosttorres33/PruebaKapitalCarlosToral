package com.carlostorres.pruebakapitalcarlostoral.data.remote.model

data class CardInfo(
    val archetype: String,
    val atk: Int,
    val attribute: String,
    val banlist_info: BanlistInfo,
    val card_images: List<CardImage>,
    val card_prices: List<CardPrice>,
    val card_sets: List<CardSet>,
    val def: Int,
    val desc: String,
    val frameType: String,
    val humanReadableCardType: String,
    val id: Int,
    val level: Int,
    val linkmarkers: List<String>,
    val linkval: Int,
    val monster_desc: String,
    val name: String,
    val pend_desc: String,
    val race: String,
    val scale: Int,
    val type: String,
    val typeline: List<String>,
    val ygoprodeck_url: String
)