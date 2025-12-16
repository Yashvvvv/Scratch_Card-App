# 🎴 Scratch Card App

A modern **Android Scratch Card** built with **Jetpack Compose** — scratch to reveal exciting rewards!

---

## ✨ Features

- 🖐️ **Touch-based scratching** — Rub the card to reveal hidden rewards
- 🎁 **Smooth reveal** — Content appears after scratching threshold
- 🎨 **Modern UI** — Material 3 design with Jetpack Compose
- 📱 **Responsive layout** — Works on all screen sizes
- 📋 **Terms & Conditions** — Bottom sheet with offer details

---

## 🛠️ Tech Stack

| Technology | Purpose |
|------------|---------|
| **Kotlin** | Programming language |
| **Jetpack Compose** | Modern UI toolkit |
| **Material 3** | Design system |
| **ViewModel + StateFlow** | State management |
| **Canvas API** | Scratch effect |

---

## 📁 Project Structure

```
app/src/main/java/com/example/scratch/
├── MainActivity.kt
├── data/model/
│   └── ScratchReward.kt
└── ui/
    ├── theme/
    └── scratchcard/
        ├── ScratchCardScreen.kt
        ├── ScratchCardViewModel.kt
        └── components/
            ├── ScratchCanvas.kt
            ├── RevealedContent.kt
            └── TermsAndConditionsSheet.kt
```

---

## 🔧 How It Works

### Scratch Detection
```kotlin
detectDragGestures(
    onDrag = { change, _ ->
        path.lineTo(change.position.x, change.position.y)
    }
)
```

### Reveal Mechanism
```kotlin
drawPath(
    path = scratchPath,
    color = Color.Transparent,
    blendMode = BlendMode.Clear
)
```

---

## 🚀 Getting Started

1. Clone the repository
   ```bash
   git clone https://github.com/Yashvvvv/Scratch_Card-App.git
   ```

2. Open in Android Studio

3. Build & Run on emulator or device

4. Scratch the card to reveal your reward! 🎉

---

## 👨‍💻 Author

**Yash**
