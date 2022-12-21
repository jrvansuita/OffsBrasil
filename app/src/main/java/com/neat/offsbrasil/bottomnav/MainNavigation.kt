package com.neat.offsbrasil.bottomnav

import android.annotation.SuppressLint
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.web.AccompanistWebChromeClient
import com.neat.offsbrasil.R
import com.neat.offsbrasil.downloader.DownloaderWrapper
import com.neat.offsbrasil.extensions.launchShareApp
import com.neat.offsbrasil.theme.AppTheme
import com.neat.offsbrasil.ui.main.MainScreen
import com.neat.offsbrasil.ui.support.SupportScreen


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainNavigationScreen(
	webChromeClient: AccompanistWebChromeClient,
	downloaderWrapper: DownloaderWrapper
) {
	val navController = rememberNavController()

	AppTheme {
		Scaffold(
			bottomBar = { CreateBottomNavigation(navController = navController) }
		) {
			NavHost(navController, startDestination = BottomNavItems.Main.route) {
				composable(BottomNavItems.Support.route) {
					SupportScreen()
				}
				composable(BottomNavItems.Main.route) {
					MainScreen(webChromeClient, downloaderWrapper)
				}
			}
		}
	}
}


@Composable
fun CreateBottomNavigation(navController: NavController) {

	val items = listOf(
		BottomNavItems.Support,
		BottomNavItems.Main,
		BottomNavItems.Share
	)

	BottomNavigation(
		backgroundColor = Color.White,
		contentColor = Color.Gray
	) {

		val navBackStackEntry by navController.currentBackStackEntryAsState()
		val currentRoute = navBackStackEntry?.destination?.route
		val context = LocalContext.current

		items.forEach {
			BottomNavigationItem(
				icon = {
					Icon(
						painter = painterResource(it.iconRes),
						contentDescription = stringResource(it.titleRes)
					)
				},
				label = { Text(text = stringResource(it.titleRes)) },
				selectedContentColor = Color.Black,
				unselectedContentColor = Color.Gray,
				alwaysShowLabel = true,
				selected = currentRoute == it.route,
				onClick = {
					if (it.route == BottomNavItems.Share.route) {
						context.launchShareApp(R.string.share_app_message)
					} else {
						navigate(navController, it)
					}
				}
			)
		}
	}
}

private fun navigate(
	navController: NavController,
	item: BottomNavItems
) {
	navController.navigate(item.route) {

		navController.graph.startDestinationRoute?.let { route ->
			popUpTo(route) {
				saveState = true
			}
		}
		launchSingleTop = true
		restoreState = true
	}
}

