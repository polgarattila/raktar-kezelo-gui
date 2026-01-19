# AtiRaktarKezelo 📦

<p align="center">
  <i>"That's my first steps on my journey to become a Java Developer. :)"</i>
</p>

---

## 📥 Download & Try
You can find the latest stable version of the application here:
👉 **[Download AtiRaktarKezelo v1.0](https://github.com/polgarattila/raktar-kezelo-gui/releases/latest)**

---

## 📝 Application Description
This is a professional **Warehouse Management System (WMS)** designed to streamline inventory tracking and logistics. The application features a modern graphical user interface (GUI) and provides a robust solution for managing stock levels efficiently.

### ✨ Key Features
* **Real-time Inventory Management**: Track items, quantities, and stock movements. 📈
* **User-Friendly Interface**: Built with JavaFX for a smooth desktop experience. 🖥️
* **Automated Installer**: Packaged with a custom runtime (using `jlink` and `jpackage`) so it runs on any Windows machine. ⚙️

---

## 💻 Technical Stack

| Component | Technology |
| :--- | :--- |
| **Language** | Java 21 ☕ |
| **GUI Framework** | JavaFX |
| **Build Tool** | Maven |
| **Deployment** | Custom JRE runtime packaging |

---

## 🇭🇺 Language / Nyelv
The application interface is in **Hungarian**.

---

## 🧠 Technical Challenges & Solutions
* **Modular Deployment**: Optimized the application size by using `jlink` to create a custom, lightweight Java Runtime Image (JRE).
* **Native Packaging**: Configured `jpackage` to generate a professional Windows installer (`.exe`) with a custom icon 🖼️ and desktop shortcuts.
* **UI/UX Design**: Implemented a responsive layout using JavaFX and FXML for an intuitive user experience.

---

## ⚙️ How to Run

### Option 1: Using the Installer (Recommended)
1. Download the `AtiRaktarKezelo-1.0.exe` from the **Download** link above.
2. Run the installer and follow the instructions.

### Option 2: Running from Source
```bash
# Clone the repository
git clone [https://github.com/polgarattila/raktar-kezelo-gui.git](https://github.com/polgarattila/raktar-kezelo-gui.git)

# Navigate to the folder
cd raktar-kezelo-gui

# Build and run
mvn javafx:run
