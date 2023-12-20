package com.mesum.domain.model




data class CoinDetail(
    val coinId: String?,
    val name: String?,
    val description: String?,
    val symbol: String?,
    val rank: Int?,
    val isActive: Boolean?,
    val tags: List<String>?,
    val team: List<TeamMember?>?
)

data class TeamMember(
    var id: String,
    val name: String,
    val position: String,

    // other fields relevant to the domain
)