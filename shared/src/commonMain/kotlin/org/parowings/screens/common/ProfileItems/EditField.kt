package org.parowings.screens.common.ProfileItems

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.parowings.theming.BackgroundLight
import org.parowings.theming.PrimaryGreen
import org.parowings.theming.TextDark
import org.parowings.theming.TextGray

@Composable
fun EditField(
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: ImageVector,
    modifier: Modifier = Modifier,
    isMultiline: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = if (isMultiline) ImeAction.Default else ImeAction.Next,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {

    var isFocused by remember { mutableStateOf(false) }

    val borderColor by animateColorAsState(
        targetValue = if (isFocused) PrimaryGreen else BackgroundLight,
        animationSpec = tween(250),
        label = ""
    )

    val selectionColors = TextSelectionColors(
        handleColor = PrimaryGreen,
        backgroundColor = PrimaryGreen.copy(alpha = 0.25f)
    )

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (isMultiline)
                    Modifier.height(110.dp)
                else
                    Modifier.height(56.dp)
            )
            .border(
                width = if (isFocused) 2.dp else 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 2.dp
    ) {

        Box(Modifier.fillMaxSize()) {

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = 16.dp,
                        vertical = if (isMultiline) 16.dp else 0.dp
                    ),
                verticalAlignment = if (isMultiline)
                    Alignment.Top
                else
                    Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = if (isFocused)
                        PrimaryGreen
                    else
                        TextGray.copy(alpha = .7f),
                    modifier = Modifier.size(20.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                CompositionLocalProvider(
                    LocalTextSelectionColors provides selectionColors
                ) {

                    BasicTextField(
                        value = value,
                        onValueChange = onValueChange,
                        singleLine = !isMultiline,
                        interactionSource = remember { MutableInteractionSource() },
                        cursorBrush = SolidColor(PrimaryGreen),

                        keyboardOptions = KeyboardOptions(
                            keyboardType = keyboardType,
                            imeAction = imeAction
                        ),

                        keyboardActions = keyboardActions,

                        textStyle = TextStyle(
                            color = TextDark,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium
                        ),

                        modifier = Modifier
                            .fillMaxWidth()
                            .onFocusChanged {
                                isFocused = it.isFocused
                            },

                        decorationBox = { innerTextField ->

                            Box {

                                if (value.isEmpty()) {
                                    Text(
                                        text = placeholder,
                                        color = TextGray.copy(alpha = .65f),
                                        fontSize = 15.sp
                                    )
                                }

                                innerTextField()
                            }
                        }
                    )
                }
            }

            if (isMultiline) {

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(8.dp)
                ) {

                    Canvas(
                        modifier = Modifier.size(10.dp)
                    ) {

                        drawLine(
                            color = TextGray.copy(alpha = .35f),
                            start = Offset(size.width, 0f),
                            end = Offset(0f, size.height),
                            strokeWidth = 2f
                        )

                        drawLine(
                            color = TextGray.copy(alpha = .35f),
                            start = Offset(size.width, size.height * .5f),
                            end = Offset(size.width * .5f, size.height),
                            strokeWidth = 2f
                        )
                    }
                }
            }
        }
    }
}