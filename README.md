# Monk Mod 1.20.1 port

This repository is a Forge 1.20.1 migration scaffold for the old Monk Mod from 1.12.2.
The original codebase is heavily version-dependent, so this project starts with the modern Forge structure and a minimal working foundation.

## Included
- Forge 1.20.1 project setup
- basic item registration
- player capability with saved monk level/progress
- simple network sync for client/server data
- startup mod class and event hooks

## Structure
- `src/main/java/com/rwtema/monkmod` — mod code
- `src/main/resources/assets/monk` — assets
- `src/main/resources/META-INF/mods.toml` — mod metadata

## Next steps
Port the original Monk abilities one by one from the 1.12.2 code, because many classes and APIs changed significantly between versions.
