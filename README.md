# Multithreaded Java Program for Scribe Resource Management

This program simulates a system where multiple scribes work concurrently to record information using shared resources: pens and ink bottles. The program manages the allocation of these resources in a multithreaded environment, ensuring that no two scribes can use the same resource at the same time.

## Overview
The task involves simulating multiple scribes (represented as threads) performing actions in parallel. Each scribe needs a pen and an ink bottle to write a record. The resources are limited, so careful management of these shared resources is essential to avoid issues such as deadlock, starvation, and busy-waiting.

### Key Features:
- **Multithreading**: Each scribe operates as an independent thread, performing actions concurrently.
- **Concurrency Control**: Scribes must synchronize access to shared resources (pen and ink bottle) to avoid conflicts.
- **Deadlock Prevention**: The system handles situations to ensure scribes can continue working without getting stuck waiting for resources.
- **Action Coordination**: Actions such as picking up a pen, taking an ink bottle, writing a record, and returning resources are managed to avoid unnecessary delays and ensure fairness.
