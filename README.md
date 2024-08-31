# Paymob Mobile Technical Task

## Overview

This repository contains the Paymob Mobile Technical Task,
the app displays a list of the best movies in 2024. Each movie should be clickable, leading to a
details page containing all relevant information.

## Architecture

### MVVM (Model-View-ViewModel)

- Separation of Concerns: Divides the application into three main components, promoting a clear
  separation of concerns.
- Testability: Facilitates unit testing of business logic.
- Two-Way Data Binding: Simplifies the synchronization between the UI and the underlying data.
- Flexibility: Adapts well to changing requirements and complex UI interactions.

## Libraries & Tools Used

- Clean Architecture: Separates the app into layers to promote a scalable and maintainable codebase.
- Multi-Module: Modularizes the app into separate modules to improve build times and
  maintainability.
- View Binding: Allows easier interaction with views.
- Navigation: Implements navigation, from simple button clicks to complex patterns like app bars and
  navigation drawers.
- ViewModel: Stores and manages UI-related data in a lifecycle-conscious way.
- Repository: Mediates between different data sources, such as persistent models, web services, and
  caches.
- Kotlin Coroutines: Simplifies code that executes asynchronously.
- Retrofit: A type-safe HTTP client for Android and Java.
- DataStore: Stores key-value pairs or typed objects with protocol buffers.
- Dagger Hilt: Reduces the boilerplate of manual dependency injection.
- Coil: An image loading library backed by Kotlin Coroutines.

## Comparison of Tools

### DataStore vs Shared Preferences

#### Shared Preferences

- Ease of Use: Easy to use and requires minimal setup.
- Ideal for Small Data: Suitable for storing small amounts of data such as user preferences,
  settings, and session information.
- Limitations: Not safe to call on the UI thread as operations are synchronous and may block the
  main thread. Supports only primitive data types and does not provide type safety.

#### DataStore

- Complex Data Objects: Can handle complex data objects through serialization.
- Asynchronous Operations: Built-in support for asynchronous operations using Kotlin Coroutines.
- Observability: Offers observability through Flow or LiveData.
- Support for Encryption: Provides an added layer of protection with support for encryption.

Summary: While Shared Preferences are suitable for storing small amounts of primitive data types,
DataStore provides a more robust and efficient solution for storing complex data objects, with
support for asynchronous operations, observability, and encryption.

### Coil vs Glide

#### Glide

- Familiar API: Offers a familiar API and is adept at handling complex image loading scenarios.
- Robust: Robust caching mechanisms, seamless fetching of images, and ease of integration.

#### Coil

- Modern: A modern and lightweight image loading library designed with a focus on modern Android
  development practices.
- Kotlin Integration: Leverages Kotlin’s coroutines and extension functions to simplify the process
  of image loading while promoting clean and concise code.
- Lightweight: Excels in providing the essentials without unnecessary overhead.
- Automatic Memory Management: Ensures that memory management is automated and optimized, preventing
  memory leaks and out-of-memory errors.

Summary: While Glide is a powerful toolset for handling complex image needs, Coil is a modern,
lightweight approach that values memory efficiency and integrates well with Kotlin.

## Project Structure

This app is structured as a multi-module project for better separation of concerns and scalability.
The main modules include:

- app: Manages UI components and user interactions.
- data: Handles data management and repository implementations.
- domain: Contains use cases and business logic.
