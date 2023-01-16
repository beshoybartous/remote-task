package com.example.simpleremotetask.features.login.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.simpleremotetask.common.ui.components.LoadingScreen
import com.example.simpleremotetask.features.login.presentation.viewmodel.AuthUiEvent
import com.example.simpleremotetask.features.login.presentation.viewmodel.LoginViewModel
import com.example.simpleremotetask.navigation.Screen
import com.example.simpleremotetask.network.NetworkResult

@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    LaunchedEffect(loginViewModel, context) {
        loginViewModel.loginFlow.collect {
            when (it) {
                is NetworkResult.Error -> {
                    Toast.makeText(
                        context,
                        "An unknown error occurred",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is NetworkResult.Success -> {
                    navController.popBackStack()
                    navController.navigate(
                        Screen.Home.passUserName(
                            it.data?.userName ?: ""
                        )
                    )
                }
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Login",
                style = TextStyle(
                    fontSize = 40.sp,
                    fontFamily = FontFamily.Serif
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            UserNameTextField(
                label = "Username",
                value = loginViewModel.state.userName,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                onValueChanged = loginViewModel::onEvent
            )

            Spacer(modifier = Modifier.height(20.dp))

            PasswordTextField(
                label = "Password",
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                ),
                value = loginViewModel.state.password,
                onValueChanged = loginViewModel::onEvent
            )

            Spacer(modifier = Modifier.height(20.dp))

            LoginButton(
                text = "Login",
                enabled = (loginViewModel.state.password.isNotEmpty() &&
                        loginViewModel.state.password.isNotBlank() &&
                        loginViewModel.state.userName.isNotEmpty() &&
                        loginViewModel.state.userName.isNotBlank()),
                onClick = loginViewModel::onEvent
            )

        }
        LoadingScreen(show = loginViewModel.state.isLoading)
    }
}

@Composable
fun UserNameTextField(
    label: String,
    value: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChanged: (AuthUiEvent.LoginUsernameChanged) -> Unit
) {
    TextField(
        label = { Text(text = label) },
        value = value,
        singleLine = true,
        keyboardOptions = keyboardOptions,
        onValueChange = {
            onValueChanged(AuthUiEvent.LoginUsernameChanged(it))
        })
}

@Composable
fun PasswordTextField(
    label: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    value: String,
    onValueChanged: (AuthUiEvent.LoginPasswordChanged) -> Unit
) {
    TextField(
        label = { Text(text = label) },
        value = value,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        singleLine = true,
        onValueChange = {
            onValueChanged(AuthUiEvent.LoginPasswordChanged(it))
        })
}

@Composable
fun LoginButton(
    text: String,
    enabled: Boolean,
    onClick: (AuthUiEvent.Login) -> Unit
) {
    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
        Button(
            enabled = enabled,
            onClick = {
                onClick(AuthUiEvent.Login)
            },
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = text)
        }
    }
}