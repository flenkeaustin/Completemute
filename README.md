安装后在拨号盘输入 `*#*#5858#*#*` 可打开本应用(dialog in 8 ok,  but 10 not response)


In Android O(8)+, has a problem while phone in Disturb state:
```text
D/AndroidRuntime: Shutting down VM
E/AndroidRuntime: FATAL EXCEPTION: main
    Process: com.first.tile, PID: 3629
    java.lang.SecurityException: Not allowed to change Do Not Disturb state
        at android.os.Parcel.createException(Parcel.java:2071)
        at android.os.Parcel.readException(Parcel.java:2039)
        ...
```