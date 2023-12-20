package com.mesum.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.mesum.cleancrypto.data.remote.dto.Links
import com.mesum.cleancrypto.data.remote.dto.LinksExtended
import com.mesum.cleancrypto.data.remote.dto.Tag
import com.mesum.cleancrypto.data.remote.dto.Whitepaper
import com.mesum.domain.model.CoinDetail

data class CoinDetailDto(
    @SerializedName("description")
    var description: String? = "",
    @SerializedName("development_status")
    var developmentStatus: String? = "",
    @SerializedName("first_data_at")
    var firstDataAt: String? = "",
    @SerializedName("hardware_wallet")
    var hardwareWallet: Boolean? = false,
    @SerializedName("hash_algorithm")
    var hashAlgorithm: String? = "",
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("is_active")
    var isActive: Boolean? = false,
    @SerializedName("is_new")
    var isNew: Boolean? = false,
    @SerializedName("last_data_at")
    var lastDataAt: String? = "",
    @SerializedName("links")
    var links: Links? = Links(),
    @SerializedName("links_extended")
    var linksExtended: List<LinksExtended?>? = listOf(),
    @SerializedName("logo")
    var logo: String? = "",
    @SerializedName("message")
    var message: String? = "",
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("open_source")
    var openSource: Boolean? = false,
    @SerializedName("org_structure")
    var orgStructure: String? = "",
    @SerializedName("proof_type")
    var proofType: String? = "",
    @SerializedName("rank")
    var rank: Int? = 0,
    @SerializedName("started_at")
    var startedAt: String? = "",
    @SerializedName("symbol")
    var symbol: String? = "",
    @SerializedName("tags")
    var tags: List<Tag?>? = listOf(),
    @SerializedName("team")
    var team: List<TeamMember?>? = listOf(),
    @SerializedName("type")
    var type: String? = "",
    @SerializedName("whitepaper")
    var whitepaper: Whitepaper? = Whitepaper()
)

fun CoinDetailDto.toCoinDetail(): CoinDetail {
    return CoinDetail(
        coinId = id,
        name = name,
        description = description,
        symbol =  symbol,
        tags = tags?.map { it?.name ?: "" },
        team = team?.map { dtoTeamMember ->
            dtoTeamMember?.id?.let {
                dtoTeamMember.name?.let { it1 ->
                    dtoTeamMember.position?.let { it2 ->
                        com.mesum.domain.model.TeamMember(
                            id = it,
                            name = it1,
                            position = it2
                        )
                    }
                }
            }

        },
        rank = rank,
        isActive = isActive
    )
}