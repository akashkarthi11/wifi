# Panasonic AC Remote prototype
Android project for Panasonic CS-VU18AKYF.

GitHub Actions builds a debug APK on pushes to main or manual workflow dispatch. Download the PanasonicACRemote-debug-apk artifact from the Actions run.

Important: this prototype provides a basic UI and IR hardware detection only. It does not yet implement exact Panasonic IR command frames or Matter commissioning/control. Its controls do not operate the AC yet. IR requires an IR emitter in the Android phone.
