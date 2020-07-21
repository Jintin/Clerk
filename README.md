# Clerk

[![Build Status](https://travis-ci.org/Jintin/Clerk.svg?branch=master)](https://travis-ci.org/Jintin/Clerk)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/60b7174503e047ae82202081ada2d9ee)](https://app.codacy.com/app/Jintin/Clerk?utm_source=github.com&utm_medium=referral&utm_content=Jintin/Clerk&utm_campaign=Badge_Grade_Dashboard)
[![Maintainability](https://api.codeclimate.com/v1/badges/ebee76105bbc0c162fd1/maintainability)](https://codeclimate.com/github/Jintin/Clerk/maintainability)
[![Download](https://api.bintray.com/packages/jintin/maven/Clerk/images/download.svg) ](https://bintray.com/jintin/maven/Clerk/_latestVersion)

Clerk is a log service to collect and display your app log on Android devices.

## Install

* Simple install by gradle dependency to your project
```groovy
implementation 'com.github.jintin:clerk:1.1'
```
* Install the agent app on [Google Play](https://play.google.com/store/apps/details?id=com.jintin.clerk) or build from `app` module.

## Usage

Clerk send log information by Android broadcast functionality. There are two ways to do that:

1. Installing the `lib` module by gradle and calling the built-in function `ClerkUtils.log()` with require parameter. You can get more information by tracing the [sample module](https://github.com/Jintin/Clerk/tree/master/sample)
2. Sending the broadcast by your own follow by the interface:
```kotlin
Intent("com.jintin.clerk.LOG_ACTION").also {
    it.putExtra("data", "your Log information")
    it.putExtra("channel", "specific channel for search (optional)")
    it.putExtra("app", "your packagename")
    it.component = ComponentName("com.jintin.clerk", "com.jintin.clerk.app.LogReceiver")
    context.sendBroadcast(it)
}
```

## Contributing

Bug reports and pull requests are welcome on GitHub at <https://github.com/Jintin/Clerk>.

## License

The module is available as open source under the terms of the [MIT License](http://opensource.org/licenses/MIT).
