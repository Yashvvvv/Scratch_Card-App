# 🎴 Scratch Card App

A modern **Android Scratch Card** built with **Jetpack Compose** — scratch to reveal exciting rewards!

---

## ✨ Features

- 🖐️ **Touch-based scratching** — Rub the card to reveal hidden rewards
- 🎁 **Animated reveal** — Smooth transition when reward is uncovered
- 🎨 **Modern UI** — Material 3 design with Jetpack Compose
- 📱 **Responsive layout** — Works on all screen sizes
- 📋 **Terms & Conditions** — Bottom sheet with offer details

---

## 📸 Demo

| Before Scratch | After Scratch |
|----------------|---------------|
| Gift box overlay visible | Reward revealed with coupon code |

---

## 🛠️ Tech Stack

| Technology | Purpose |
|------------|---------|
| **Kotlin** | Programming language |
| **Jetpack Compose** | Modern UI toolkit |
| **Material 3** | Design system |
| **ViewModel + StateFlow** | State management |
| **Canvas API** | Scratch effect drawing |

---

## 📁 Project Structure

```
app/src/main/java/com/example/scratch/
├── MainActivity.kt
├── data/model/
│   └── ScratchReward.kt
└── ui/
    ├── theme/
    │   ├── Color.kt
    │   ├── Theme.kt
    │   └── Type.kt
    └── scratchcard/
        ├── ScratchCardScreen.kt
        ├── ScratchCardViewModel.kt
        ├── ScratchCardUiState.kt
        └── components/
            ├── ScratchCanvas.kt
            ├── RevealedContent.kt
            └── TermsAndConditionsSheet.kt
```

---

## 🔧 How It Works

### 1. Scratch Detection
```kotlin
detectDragGestures(
    onDrag = { change, _ ->
        // Track finger movement and create scratch path
        path.lineTo(change.position.x, change.position.y)
    }
)
```

### 2. Reveal Mechanism
```kotlin
drawPath(
    path = scratchPath,
    color = Color.Transparent,
    blendMode = BlendMode.Clear  // Erases the overlay
)
```

### 3. State Management
```kotlin
class ScratchCardViewModel : ViewModel() {
    private val revealThreshold = 0.70f  // 70% scratched = revealed
    
    fun updateScratchProgress(progress: Float) {
        // Update state and trigger reveal
    }
}
```

---

## 🚀 Getting Started

1. **Clone the repository**
   ```bash
   git clone https://github.com/Yashvvvv/Scratch_Card-App.git
   ```

2. **Open in Android Studio**

3. **Build & Run** on emulator or device

4. **Scratch the card** to reveal your reward! 🎉

---

## 📦 Dependencies

```kotlin
// Jetpack Compose
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.material3:material3")
implementation("androidx.lifecycle:lifecycle-viewmodel-compose")
```

---

## 🎯 Key Features Implementation

| Feature | Implementation |
|---------|----------------|
| Scratch Effect | Canvas + BlendMode.Clear |
| Touch Detection | Compose Gestures API |
| State Management | ViewModel + StateFlow |
| Reveal Animation | Conditional Composables |
| Bottom Sheet | ModalBottomSheet |

---

## 👨‍💻 Author

**Yash**

---

## 📄 License

This project is for educational purposes.

