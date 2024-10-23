package com.emircivelek.redpay.common.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AuthSwitch(){
    var checked by remember { mutableStateOf(false) }

    Switch(
        checked = checked,
        onCheckedChange = {
            checked = it
        },
        thumbContent = if (checked) {
            {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    modifier = Modifier.size(SwitchDefaults.IconSize),
                    tint = Color.White
                )
            }
        } else {
            null
        }
        , colors = SwitchDefaults.colors(
            checkedThumbColor = Color.Black,
            checkedTrackColor = Color.Black,
            uncheckedTrackColor = Color.Transparent,
            uncheckedThumbColor = Color.Black,
            uncheckedBorderColor = Color.Black  ,
            checkedBorderColor = Color.Black,
    ))
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun AuthSwitchPreview(){
    AuthSwitch()
}