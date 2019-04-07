# Clerk
Clerk is a log service to collect and display your app log on Android devices.

## Usage
1. Build app module and install on your Android device.
2. Send log information by Android broadcast functionality. There are two ways to do that:
   - Clone the `lib` module and calling the built-in function `ClerkUtils.log()` with require parameter. You can get more information by tracing the [sample module](https://github.com/Jintin/Clerk/tree/master/sample)
   - You can also send the broadcast by your own follow by the interface:
   ```
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
