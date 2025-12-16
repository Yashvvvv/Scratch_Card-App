# Scratch Card App - Complete Code Explanation

A modern Android scratch card implementation built with Jetpack Compose. Users can scratch/rub an overlay image to reveal rewards underneath.

---

## Project Structure

```
app/src/main/java/com/example/scratch/
├── MainActivity.kt                          # Entry point
├── data/
│   └── model/
│       └── ScratchReward.kt                 # Reward data model
└── ui/
    ├── theme/
    │   ├── Color.kt                         # App colors
    │   ├── Theme.kt                         # Material theme
    │   └── Type.kt                          # Typography
    └── scratchcard/
        ├── ScratchCardScreen.kt             # Main scratch card UI
        ├── ScratchCardViewModel.kt          # State management
        ├── ScratchCardUiState.kt            # UI state data class
        └── components/
            ├── ScratchCanvas.kt             # Scratch/rub functionality
            ├── RevealedContent.kt           # Content shown after scratching
            └── TermsAndConditionsSheet.kt   # Bottom sheet for T&C

app/src/main/res/drawable/
└── google_scratch_card.png                  # Scratch overlay image
```

---

## Key Components

### 1. ScratchCardScreen.kt
The main composable that orchestrates the entire scratch card experience.

**Features:**
- Fullscreen overlay with semi-transparent background
- Close button with padding above the card
- Decorative side clips (purple semicircles)
- Main scratch card body (280dp x 300dp)
- "Claim offer now" button (appears only after scratching)
- "Terms & Conditions" link (appears only after scratching)
- Bottom sheet for terms and conditions

```kotlin
@Composable
fun ScratchCardScreen(
    viewModel: ScratchCardViewModel,
    onClaimOffer: (String) -> Unit,
    onDismiss: () -> Unit
)
```

**Key UI Elements:**
- Close button: 16dp padding below it before the card
- Card dimensions: 280dp width x 300dp height
- Claim button and Terms link only show when `uiState.isScratched` is true

---

### 2. ScratchCanvas.kt
Handles the scratch/rub interaction using an image overlay.

**How it works:**
1. Displays `google_scratch_card.png` as the scratch overlay
2. Tracks finger drag gestures to create scratch paths
3. Uses `BlendMode.Clear` to erase the image where user scratches
4. Reports scratch progress to trigger reveal

```kotlin
@Composable
fun ScratchCanvas(
    modifier: Modifier = Modifier,
    onScratchProgress: (Float) -> Unit
)
```

**Technical Details:**
- Uses `graphicsLayer { alpha = 0.99f }` to enable proper blend modes
- Stroke width: 50dp for scratch paths
- Uses `StrokeCap.Round` and `StrokeJoin.Round` for smooth scratching
- Tracks paths in a list for persistent scratch marks

---

### 3. RevealedContent.kt
Displays the reward content that gets revealed after scratching.

**Content shown:**
- Reward amount (e.g., "$10")
- Reward label (e.g., "Cashback")
- Gift icon (Canvas-drawn)
- Offer title
- Offer description
- Coupon code in a yellow-bordered box

```kotlin
@Composable
fun RevealedContent(
    reward: ScratchReward,
    modifier: Modifier = Modifier
)
```

---

### 4. ScratchCardViewModel.kt
Manages the scratch card state using Kotlin StateFlow.

**State Management:**
- `isCardVisible`: Controls card visibility
- `isScratched`: True when scratch threshold is reached
- `scratchProgress`: Tracks how much has been scratched (0.0 to 1.0)
- `showTermsSheet`: Controls terms bottom sheet visibility
- `reward`: The current reward data

**Key Functions:**
- `updateScratchProgress(progress)`: Updates scratch progress, triggers reveal at threshold
- `dismissCard()`: Hides the scratch card
- `showTermsAndConditions()`: Shows the T&C bottom sheet
- `hideTermsAndConditions()`: Hides the T&C bottom sheet

---

### 5. ScratchReward.kt
Data class representing the reward shown after scratching.

```kotlin
data class ScratchReward(
    val amount: String = "$10",
    val label: String = "Cashback",
    val title: String = "Offer from AppStorys",
    val description: String = "Cashback on mobile and recharge",
    val couponCode: String = "APPSTORYS50",
    val claimUrl: String = "https://www.google.com/search?q=offers"
)
```

---

## Theme Colors (Color.kt)

| Color Name | Value | Usage |
|------------|-------|-------|
| `ScratchCardBackground` | Dark gray/black | Main card background |
| `ScratchBlue` | Blue | Scratch overlay base |
| `ClaimButtonBlue` | Blue | Claim button |
| `CouponBorderYellow` | Yellow | Coupon code border |
| `CardClipPurple` | Purple | Side clip decorations |
| `OverlayBackground` | Semi-transparent black | Fullscreen overlay |

---

## User Flow

1. **Card Appears**: Fullscreen overlay with scratch card in center
2. **User Scratches**: Rubs finger on the gift box image to reveal content
3. **Content Revealed**: After scratching enough, the overlay disappears
4. **Buttons Appear**: "Claim offer now" and "Terms & Conditions" appear below the card
5. **Claim or Dismiss**: User can claim the offer or close the card

---

## Key Implementation Details

### Scratch Mechanism
```kotlin
// In ScratchCanvas.kt
Canvas(modifier = Modifier.pointerInput(Unit) {
    detectDragGestures(
        onDragStart = { offset -> /* Start new path */ },
        onDrag = { change, _ -> /* Extend path */ },
        onDragEnd = { /* Save path */ }
    )
}) {
    // Draw scratch paths with BlendMode.Clear to erase overlay
    drawPath(
        path = path,
        color = Color.Transparent,
        blendMode = BlendMode.Clear
    )
}
```

### Conditional Button Display
```kotlin
// In ScratchCardScreen.kt
if (uiState.isScratched) {
    ClaimButton(onClick = { onClaimOffer(uiState.reward.claimUrl) })
    TermsAndConditionsLink(onClick = { viewModel.showTermsAndConditions() })
}
```

---

## Dependencies

- Jetpack Compose UI
- Compose Material 3
- Compose Foundation (gestures)
- ViewModel with StateFlow

---

## File: google_scratch_card.png

The scratch overlay image featuring:
- Blue background with confetti pattern
- Darker blue cross/ribbon design
- Center circle with gift box icon

This image is displayed as the scratch surface and gets erased as the user rubs it.

---

## Recent Updates (December 2024)

1. **Added padding** between close button and scratch card (8dp → 16dp)
2. **Moved buttons outside card**: "Claim offer now" and "Terms & Conditions" are now positioned below the scratch card, not inside it
3. **Conditional button display**: Buttons only appear after the card is scratched
4. **Image-based scratch overlay**: Replaced Canvas-drawn overlay with PNG image (`google_scratch_card.png`)
5. **Simplified ScratchCanvas**: Now uses Image composable with BlendMode.Clear for erasing
6. **Reduced card height**: 380dp → 300dp to accommodate external buttons

---

## Building & Running

1. Open project in Android Studio
2. Ensure `google_scratch_card.png` is in `res/drawable/`
3. Build and run on device/emulator
4. Scratch the card to reveal the reward!

