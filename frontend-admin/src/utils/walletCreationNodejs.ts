// npm install -g typescript
// tsc walletCreation.ts
// node walletCreation.cjs

import { randomBytes } from 'crypto';
const { ec } = require('elliptic');
const { keccak256 } = require('web3-utils');
 
const ellipticCurve = new ec('secp256k1');


// 랜덤한 32바이트(256비트)를 생성하여 개인키로 사용
function generatePrivateKey(): string {
    const privateKeyBytes = randomBytes(32);
    const privateKeyHex = privateKeyBytes.toString('hex');
    return privateKeyHex;
}

// 공개키 생성
function getPublicKey(privateKey:string) {
  // hex로 private key 저장
  const keypair = ellipticCurve.keyFromPrivate(privateKey, 'hex');
  // private key로 public key 얻기
  const publicKey = keypair.getPublic('hex')
  return publicKey;
}


// 주소 생성
function getEthereumAddress(publicKey:string) {
  // Buffer : Node.js의 내장 클래스로, 16진수로 인코딩된 데이터를 이진 데이터로 변환
  const publickeyBuffer = Buffer.from(publicKey, 'hex');
  // 공개키 버퍼로 주소 얻기
  const address = '0x' + keccak256(publickeyBuffer.slice(1)).substring(26)
  return address;
}


let privateKey:string = generatePrivateKey();
const publicKey:string = getPublicKey(privateKey);
const address:string = getEthereumAddress(publicKey);

console.log('생성된 개인키:', privateKey);
console.log('생성된 개인키:', publicKey);
console.log('생성된 개인키:', address);

/**
 * 마스터링 이더리움 4장의 개인키를 입력으로 사용
 * k = f8f8a2f43c8376ccb0871305060d7b27b0554d2cc72bccf41b2705608452f315
 * 참고 링크 https://github.com/ethereumbook/ethereumbook/blob/develop/04keys-addresses.asciidoc#ethereum-addresses
 */


// 생성된 개인키를 이용하여 공개키 및 이더리움 주소 출력
if (!privateKey) {
    console.error("[Error] Enter Private Key");
} else if (privateKey.startsWith("0x")) {
    privateKey = privateKey.slice(2);
} else if (privateKey.length != 64) {
    console.error("[Error] Malformed Private Key");
} else {
    const publicKey = getPublicKey(privateKey);
    if (!publicKey.startsWith("04")) {
        console.warn("[WARN] Wrong Public Key");
    }
}
