#import "ShangPlugin.h"
#import <shang/shang-Swift.h>

@implementation ShangPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftShangPlugin registerWithRegistrar:registrar];
}
@end
