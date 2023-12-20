package com.mesum.cleancrypto.presentation.coin_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mesum.cleancrypto.data.remote.dto.TeamMember
import com.mesum.cleancrypto.domain.model.Coin
import com.mesum.cleancrypto.domain.model.CoinDetail
import com.mesum.cleancrypto.presentation.Screen
import com.mesum.cleancrypto.presentation.coin_detail.components.CoinTag
import com.mesum.cleancrypto.presentation.coin_detail.components.TeamListItem
import com.mesum.cleancrypto.presentation.coin_list.CoinListViewModel
import com.mesum.cleancrypto.presentation.coin_list.components.CoinListItem
import org.w3c.dom.Text

@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel()
) {
    
    val state by viewModel.state
    Box(modifier = Modifier.fillMaxSize()){
        state.coin?.let { coin ->
            LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(20.dp)){
              item {
                  Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                    Text(text = "${coin.rank}. ${coin.name} (${coin.symbol})", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.weight(8f))
                      Text(text = if(coin.isActive == true) "active" else "inactive", color = if (coin.isActive== true) Color.Green else Color.Red, fontStyle = FontStyle.Italic, textAlign = TextAlign.End, modifier = Modifier
                          .align(CenterVertically)
                          .weight(2f) )
                  }
                  Spacer(modifier = Modifier.height(15.dp))
                  Text(text = coin.description ?: "", style = MaterialTheme.typography.bodyMedium)
                  Spacer(modifier = Modifier.height(15.dp))
                    Text(text = "Tags", style = MaterialTheme.typography.headlineMedium)
                  Spacer(modifier = Modifier.height(15.dp))
                  com.google.accompanist.flowlayout.FlowRow (mainAxisSpacing =10.dp, crossAxisSpacing = 10.dp, modifier = Modifier.fillMaxWidth() )
                  {
                        coin.tags?.forEach { tag ->
                        CoinTag(tag = tag)
                        }
                  }
                  Text(text = "TeamMemebers", style = MaterialTheme.typography.headlineMedium)
                  Spacer(modifier = Modifier.height(15.dp))

              }
                items(coin.team ?: emptyList()){teamMember: TeamMember? ->  
                    TeamListItem(teamMember = teamMember ?: TeamMember(), modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp))
                    Divider()
                }
            }

        }

        if(state.error.isNotBlank()){
            Text(text = state.error, color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center))
        }
        if (state.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CoinDetailScreenPreview() {
    val coin = CoinDetail(
        rank = 1,
        name = "Bitcoin",
        symbol = "BTC",
        isActive = true,
        description = "This is a description of Bitcoin.",
        tags = listOf("Tag1", "Tag2"),
        team = listOf(
            TeamMember(name = "John Doe", position = "Developer"),
            TeamMember(name = "Jane Doe", position = "Designer")
        ),
        coinId = "1"
    )

    val state = CoinDetailState(
        coin = coin,
        isLoading = false,
        error = ""
    )

    Box(modifier = Modifier.fillMaxSize()) {
        state.coin?.let { coin ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.weight(8f)
                        )
                        Text(
                            text = if (coin.isActive == true) "active" else "inactive",
                            color = if (coin.isActive == true) Color.Green else Color.Red,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .align(CenterVertically)
                                .weight(2f)
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(text = coin.description ?: "", style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(text = "Tags", style = MaterialTheme.typography.headlineMedium)
                    Spacer(modifier = Modifier.height(15.dp))
                    com.google.accompanist.flowlayout.FlowRow(
                        mainAxisSpacing = 10.dp,
                        crossAxisSpacing = 10.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        coin.tags?.forEach { tag ->
                            CoinTag(tag = tag)
                        }
                    }
                    Text(
                        text = "Team Members",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }
                items(coin.team ?: emptyList()) { teamMember: TeamMember? ->
                    TeamListItem(
                        teamMember = teamMember ?: TeamMember(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                    Divider()
                }
                item {
                    Text(text = "Nigga", fontStyle = FontStyle.Italic, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                }
                items(coin.team ?: emptyList()) { teamMember: TeamMember? ->
                    TeamListItem(
                        teamMember = teamMember ?: TeamMember(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                    Divider()
                }

            }
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
