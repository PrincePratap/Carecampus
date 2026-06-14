package org.parowings.screens.animalReport

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.parowings.screens.common.FormSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimalAddress() {
    val scrollState = rememberScrollState()


    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedButton(
                    onClick = { /* Handle Cancel */ },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .padding(end = 8.dp),
                    border = BorderStroke(1.dp, LightGrayBorder)
                ) {
                    Text("Cancel", color = Color.Black)
                }
                Button(
                    onClick = { /* Handle Save */ },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .padding(start = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryPurple)
                ) {
                    Text("Save", color = Color.White)
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){

            FormSection(label = "Address Line 1") {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("House No, Street, Area") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryPurple,
                        unfocusedBorderColor = LightGrayBorder
                    )
                )
            }

            FormSection(label = "Address Line 2") {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Apartment, Building, Floor (Optional)") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryPurple,
                        unfocusedBorderColor = LightGrayBorder
                    )
                )
            }

            FormSection(label = "Landmark") {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Near Metro Station, Temple, Mall") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryPurple,
                        unfocusedBorderColor = LightGrayBorder
                    )
                )
            }

            FormSection(label = "Locality") {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Mayur Vihar Phase 1") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryPurple,
                        unfocusedBorderColor = LightGrayBorder
                    )
                )
            }

            FormSection(label = "City") {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Delhi") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryPurple,
                        unfocusedBorderColor = LightGrayBorder
                    )
                )
            }

            FormSection(label = "State") {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Delhi") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryPurple,
                        unfocusedBorderColor = LightGrayBorder
                    )
                )
            }

            FormSection(label = "Postal Code") {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("110091") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryPurple,
                        unfocusedBorderColor = LightGrayBorder
                    )
                )
            }

            FormSection(label = "Country") {
                OutlinedTextField(
                    value = "India",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryPurple,
                        unfocusedBorderColor = LightGrayBorder
                    )
                )
            }}

        }

}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun AnimalAddressPreview() {
    AnimalAddress()
}