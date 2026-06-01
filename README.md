# Music App

A modern Android music application built with Kotlin that fetches themes from an API and displays them in a tabbed interface.

## Features

- 🎵 **Tabbed Theme Categories** - Browse themes organized by categories (God, Reels, Birthday, Love, Beat Stories, etc.)
- 🖼️ **Image Gallery** - View theme thumbnails in a grid layout with 2 columns
- 🔊 **Audio Playback** - Play theme audio using Media3 ExoPlayer
- 📥 **Download Support** - Download themes for offline use
- 🌐 **REST API Integration** - Fetches data from PK Master API
- 🎨 **Material Design** - Modern UI with Material Design components
- 📱 **ViewPager2** - Smooth navigation between category tabs

## Tech Stack

- **Language:** Kotlin
- **Architecture:** MVVM (Model-View-ViewModel)
- **Networking:** Retrofit + OkHttp
- **Image Loading:** Glide
- **Media Playback:** Media3 ExoPlayer
- **UI Components:**
  - ViewPager2
  - TabLayout
  - RecyclerView
  - ConstraintLayout
- **Concurrency:** Coroutines
- **State Management:** LiveData

## Project Structure

```
app/
├── adapter/
│   ├── CategoryPagerAdapter.kt      # ViewPager adapter for category tabs
│   └── ThemeAdapter.kt              # RecyclerView adapter for themes
├── model/
│   ├── CategoryItem.kt              # Category data model
│   ├── ThemeItem.kt                 # Theme data model
│   └── ThemeResponse.kt             # API response model
├── network/
│   ├── ApiService.kt                # Retrofit API interface
│   └── RetrofitClient.kt            # Retrofit configuration
├── repository/
│   └── ThemeRepository.kt           # Data repository layer
├── ui/
│   ├── MainActivity.kt              # Main activity with tabs
│   ├── ThemeFragment.kt             # Fragment for theme grid
│   └── DetailsActivity.kt           # Details page for individual theme
├── utils/
│   ├── GlideAppModule.kt            # Glide configuration
│   └── AudioDownloader.kt           # Audio download utility
├── viewmodel/
│   ├── ThemeViewModel.kt            # ViewModel for theme data
│   └── UiState.kt                   # UI state sealed class
└── res/
    ├── layout/                      # Layout XML files
    └── drawable/                    # Drawable resources
```

## API Integration

The app uses the PK Master API to fetch themes:

```
Base URL: https://pkmaster.in/master/api/v4/
Endpoint: /getallthemes
Parameters:
  - Application_Id: 103
  - page: 0
  - languages: 287
```

## Installation

### Prerequisites
- Android Studio (latest)
- Android SDK 24+
- Kotlin 1.9+
- Gradle 8.0+

### Setup Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/mitu-3-pro/Music_App.git
   cd Music_App
   ```

2. **Open in Android Studio**
   - File → Open → Select the project folder

3. **Sync Gradle**
   - Click "Sync Now" when prompted

4. **Run the app**
   - Click the green Play button (▶️) or press `Shift + F10`

## Dependencies

```gradle
// Retrofit
implementation("com.squareup.retrofit2:retrofit:2.11.0")
implementation("com.squareup.retrofit2:converter-gson:2.11.0")
implementation("com.squareup.okhttp3:okhttp:4.12.0")

// Glide
implementation("com.github.bumptech.glide:glide:4.16.0")
implementation("com.github.bumptech.glide:okhttp3-integration:4.16.0")

// ViewPager2 & Tabs
implementation("androidx.viewpager2:viewpager2:1.1.0")
implementation("com.google.android.material:material:1.12.0")

// RecyclerView
implementation("androidx.recyclerview:recyclerview:1.4.0")

// Media3 ExoPlayer
implementation("androidx.media3:media3-exoplayer:1.5.1")
implementation("androidx.media3:media3-ui:1.5.1")

// Lifecycle & LiveData
implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.2")
implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.9.2")

// Fragment KTX
implementation("androidx.fragment:fragment-ktx:1.8.4")

// Coroutines
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")
```

## Usage

### Main Activity
The main activity fetches all themes from the API and sets up the tabbed interface:

```kotlin
val viewModel = ViewModelProvider(this).get(ThemeViewModel::class.java)
viewModel.fetchThemes()

viewModel.uiState.observe(this) { state ->
    when (state) {
        is UiState.Loading -> { /* Show loading */ }
        is UiState.Success -> {
            val adapter = CategoryPagerAdapter(this, state.data)
            viewPager.adapter = adapter
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = state.data[position].Cat_Name
            }.attach()
        }
        is UiState.Error -> { /* Show error */ }
    }
}
```

### Theme Fragment
Displays a grid of themes for each category:

```kotlin
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    
    val category = arguments?.getSerializable(ARG_CATEGORY) as? CategoryItem
    if (category != null && category.themes.isNotEmpty()) {
        val adapter = ThemeAdapter(requireContext(), category.themes)
        recyclerView.adapter = adapter
    }
}
```

## Features Implementation

### Image Loading
Images are loaded using Glide with OkHttp integration:
- Base URL: `https://pkmaster-cdn.qtonzapps.in/fullscreen/image/`
- Filename: Appended from API response (`Thumnail_Small`)
- Caching: Disk and memory cache enabled
- Placeholder: Uses app launcher icon during loading

### Audio Playback
Uses Media3 ExoPlayer for high-quality audio playback with controls.

### Data Management
- **Repository Pattern:** Separates API calls from UI logic
- **ViewModel:** Manages UI state across lifecycle changes
- **LiveData:** Observes state changes and updates UI reactively
- **Coroutines:** Handles async operations without blocking UI

## Permissions

Required permissions in `AndroidManifest.xml`:
```xml
<uses-permission android:name="android.permission.INTERNET" />
```

## Network Configuration

SSL/TLS configuration for secure API calls:
- File: `res/xml/network_security_config.xml`
- Supports HTTPS connections to PK Master CDN
- Configured for API domains

## Troubleshooting

### Images not loading
- Check internet connection
- Verify CDN URL accessibility: `https://pkmaster-cdn.qtonzapps.in/`
- Clear app cache: Settings → Apps → Music App → Storage → Clear Cache
- Rebuild project: `Build → Rebuild Project`

### API errors
- Check minSdk is 24 or higher
- Verify `Application_Id=103` is correct
- Ensure internet permission is granted
- Check network security configuration

### Audio playback issues
- Ensure Media3 dependencies are properly imported
- Check file permissions for storage access
- Verify audio file URLs are accessible

## Future Enhancements

- [ ] Download themes for offline playback
- [ ] Favorites/Bookmarks feature
- [ ] Search and filter functionality
- [ ] User authentication
- [ ] Theme previews with sample audio
- [ ] Dark mode support
- [ ] Widget support
- [ ] Push notifications for new themes

## Contributing

Feel free to fork this repository and submit pull requests for any improvements.

## License

This project is open source and available under the MIT License.

## Contact

For questions or support, please create an issue on the GitHub repository.

---

**Made with ❤️ by Mitu**
