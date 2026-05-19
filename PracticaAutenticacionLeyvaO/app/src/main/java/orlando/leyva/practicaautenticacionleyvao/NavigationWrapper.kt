package orlando.leyva.practicaautenticacionleyvao

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import orlando.leyva.practicaautenticacionleyvao.presentacion.home.HomeScreen
import orlando.leyva.practicaautenticacionleyvao.presentacion.inicial.InicialScreen
import orlando.leyva.practicaautenticacionleyvao.presentacion.login.LoginScreen
import orlando.leyva.practicaautenticacionleyvao.presentacion.signup.SignUpScreen

@Composable
fun NavigationWrapper(navHostController: NavHostController, auth: FirebaseAuth){

    val startDest = if (auth.currentUser != null) "home" else "inicial"
    NavHost(navHostController, startDestination = startDest){
        composable("inicial"){
            InicialScreen(
                navigationToLogin = {navHostController.navigate("login")},
                naviationToSignUp = {navHostController.navigate("signUp")}
            )
        }
        composable("login"){
            LoginScreen(auth){navHostController.navigate("home")}
        }
        composable("signUp") {
            SignUpScreen(auth) { navHostController.navigate("home") }
        }
        composable("home"){
            HomeScreen(auth = auth) { navHostController.navigate("inicial") }
        }
    }

}
