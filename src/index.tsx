import { NativeModules } from 'react-native';

type ZendeskType = {
  multiply(a: number, b: number): Promise<number>;
};

const { Zendesk } = NativeModules;

export default Zendesk as ZendeskType;
