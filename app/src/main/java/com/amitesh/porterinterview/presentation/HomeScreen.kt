package com.amitesh.porterinterview.presentation

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amitesh.porterinterview.R
import com.amitesh.porterinterview.util.AppConstant
import com.amitesh.porterinterview.util.AppData

@Composable
fun UserUiRoot(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.state.collectAsState()
    val context = LocalContext.current
    UserUi(uiState.value,
        modifier = modifier,
        onCopyAction = {
            Toast.makeText(
                context,
                context.getString(R.string.address_successfully_copied), Toast.LENGTH_LONG
            ).show()
        }, onClearAction = {
            viewModel.onActionClick(HomeListAction.OnClearAction)
        }, onDeleteAction = {
            viewModel.onActionClick(HomeListAction.OnDeleteAction(it))
        },
        onSearchTextChange = {
            viewModel.onActionClick(HomeListAction.OnSearchTextChange(it))
        }
    )
}

@Composable
fun UserUi(
    uiState: HomeListState,
    modifier: Modifier = Modifier,
    onCopyAction: () -> Unit,
    onClearAction: () -> Unit,
    onDeleteAction: (homeUi: HomeUi) -> Unit,
    onSearchTextChange: (String) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.baseline_keyboard_backspace_24),
                    contentDescription = stringResource(R.string.back)
                )
                Spacer(
                    modifier = Modifier
                        .width(16.dp)
                )
                Text(
                    text = stringResource(R.string.save_addresses)
                )
            }
            Row {
                OutlinedTextField(
                    value = uiState.searchQuery,
                    onValueChange = { onSearchTextChange(it) },
                    supportingText = { Text(text = stringResource(R.string.type_4_chars_to_start_searching)) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(R.string.search)
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { onClearAction() }) {
                            Image(
                                painter = painterResource(R.drawable.baseline_close_24),
                                contentDescription = stringResource(R.string.dropdown)
                            )
                        }
                    },
                    maxLines = 1,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .background(Color(0xFFF5F6FA))
                .padding(16.dp)
        ) {
            items(uiState.homeUi,
                key = { it.id }) {
                UserCardItem(it,
                    onCopyAction = { onCopyAction() },
                    onDeleteAction = { onDeleteAction(it) })
            }
        }
    }
}

@Composable
fun UserCardItem(
    homeUi: HomeUi,
    onCopyAction: () -> Unit,
    onDeleteAction: (homeUi: HomeUi) -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Icon(
                        imageVector = when (homeUi.placeType) {
                            AppConstant.OTHERS -> Icons.Default.Favorite
                            AppConstant.HOME -> Icons.Default.Place
                            AppConstant.SHOP -> Icons.Default.ShoppingCart
                            else -> Icons.Default.Info
                        },
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Column {
                        Column {
                            Row {
                                Text(homeUi.placeName, fontWeight = FontWeight.Bold)
                                Spacer(Modifier.width(8.dp))
                                if (homeUi.isMostUsed) {
                                    Text(
                                        text = stringResource(R.string.most_used),
                                        color = Color.Blue,
                                        fontSize = 8.sp,
                                        modifier = Modifier
                                            .alpha(0.9f)
                                            .background(
                                                color = Color.LightGray,
                                                shape = RoundedCornerShape(4.dp)
                                            )

                                    )
                                }
                            }

                            Text("${homeUi.userName} - ${homeUi.phoneNumber}", fontSize = 12.sp)
                        }
                    }
                }
                IconButton(
                    onClick = {
                        onCopyAction()
                    },
                    modifier = Modifier
                        .padding(0.dp)
                        .size(24.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_account_box_24),
                        contentDescription = stringResource(R.string.copy)
                    )
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                homeUi.tags.forEach { tag ->
                    AssistChip(
                        onClick = {},
                        label = { Text(tag) },
                        shape = RoundedCornerShape(8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFFF5F6FA),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = "${homeUi.addressLine}\n${homeUi.placeName}\nPincode: ${homeUi.pincode}",
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, Color(0xFF2979FF)),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(stringResource(R.string.edit), color = Color(0xFF2979FF))
                }

                Button(
                    onClick = { onDeleteAction(homeUi) },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2979FF)),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(stringResource(R.string.delete), color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserUiPreview() {
    UserUi(loadData(),
        onCopyAction = {},
        onClearAction = {},
        onSearchTextChange = {},
        onDeleteAction = {})
}

private fun loadData() = HomeListState(
    homeUi = AppData.getData(),
    searchQuery = ""
)
