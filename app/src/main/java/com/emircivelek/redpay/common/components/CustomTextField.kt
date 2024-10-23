package com.emircivelek.redpay.common.components

import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextFieldWithBottomBorder(
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    width:Float
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(width)
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            label = { Text(text = labelText, color = Color.White,fontSize = 18.sp,fontWeight = FontWeight.Bold) },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                disabledContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledTextColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                disabledBorderColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                cursorColor = Color.White

            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCustomTextField() {
    Column(modifier=Modifier.fillMaxSize().background(Color.Red)){
        CustomTextFieldWithBottomBorder("", {},"Telefon Numarası giriniz",0.9f)
    }

}