import 'package:flutter/material.dart';
import 'package:flutter_module/home_page.dart';
import 'package:flutter_module/login_page.dart';
import 'package:flutter_module/main.dart';

const String login_route = '/login';
const String main_route = "/main";
const String home_route = "/home";

final GlobalKey<NavigatorState> globalNavigatorKey = new GlobalKey<NavigatorState>();

Route<dynamic> generateRoute(RouteSettings settings) {
  String? routeName = settings.name;
  switch (routeName) {
    case login_route:
      return MaterialPageRoute(builder: (_) => LoginPage());
    case main_route:
      return MaterialPageRoute(builder: (_) => MyHomePage());
    case home_route:
      return MaterialPageRoute(builder: (_) => HomePage());
    default:
      return MaterialPageRoute(builder: (BuildContext context) {
        return Scaffold(
            body: Center(
              child: Text('找不到路由页面'),
            ),
        );
      });
  }
}

