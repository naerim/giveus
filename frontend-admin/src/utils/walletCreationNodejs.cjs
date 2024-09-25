"use strict";
// npm install -g typescript
// tsc walletCreation.ts
// node walletCreation.cjs
Object.defineProperty(exports, "__esModule", { value: true });
var crypto_1 = require("crypto");
var ec = require('elliptic').ec;
var keccak256 = require('web3-utils').keccak256;
var ellipticCurve = new ec('secp256k1');
// 랜덤한 32바이트(256비트)를 생성하여 개인키로 사용
function generatePrivateKey() {
    var privateKeyBytes = (0, crypto_1.randomBytes)(32);
    var privateKeyHex = privateKeyBytes.toString('hex');
    return privateKeyHex;
}
// 공개키 생성
function getPublicKey(privateKey) {
    // hex로 private key 저장
    var keypair = ellipticCurve.keyFromPrivate(privateKey, 'hex');
    // private key로 public key 얻기
    var publicKey = keypair.getPublic('hex');
    return publicKey;
}
// 주소 생성
function getEthereumAddress(publicKey) {
    // Buffer : Node.js의 내장 클래스로, 16진수로 인코딩된 데이터를 이진 데이터로 변환
    var publickeyBuffer = Buffer.from(publicKey, 'hex');
    // 공개키 버퍼로 주소 얻기
    var address = '0x' + keccak256(publickeyBuffer.slice(1)).substring(26);
    return address;
}
var privateKey = generatePrivateKey();
var publicKey = getPublicKey(privateKey);
var address = getEthereumAddress(publicKey);
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
}
else if (privateKey.startsWith("0x")) {
    privateKey = privateKey.slice(2);
}
else if (privateKey.length != 64) {
    console.error("[Error] Malformed Private Key");
}
else {
    var publicKey_1 = getPublicKey(privateKey);
    if (!publicKey_1.startsWith("04")) {
        console.warn("[WARN] Wrong Public Key");
    }
}
