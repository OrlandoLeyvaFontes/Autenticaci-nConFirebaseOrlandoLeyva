package orlando.leyva.practicaautenticacionleyvao

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth
import orlando.leyva.practicaautenticacionleyvao.presentacion.inicial.InicialScreen
import orlando.leyva.practicaautenticacionleyvao.presentacion.login.LoginScreen
import orlando.leyva.practicaautenticacionleyvao.presentacion.signup.SignUpScreen

@Composable
fun NavigationWrapper(navHostController: NavHostController, auth: FirebaseAuth){

    NavHost(navHostController, startDestination = "inicial"){
        composable("inicial"){
            InicialScreen(
                navigationToLogin = {navHostController.navigate("login")},
                naviationToSignUp = {navHostController.navigate("signUp")}
            )
        }
        composable("Login"){
            LoginScreen(auth)
        }
        composable("signUp") {
            SignUpScreen(auth)
        }
    }

}
