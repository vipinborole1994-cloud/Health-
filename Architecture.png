graph TD
    User([User / Browser]) -->|1. Interacts with| FE[Front-end Layer: JavaScript, React, HTML/CSS]
    User -->|2. Authorizes Transactions| Signer[Signer / Wallet: MetaMask, WalletConnect]
    Signer <-->|Injects Web3 Instance| FE
    FE <-->|3. Reads/Writes Data via JSON-RPC| Providers[Provider Layer: Alchemy, Infura, QuickNode]
    Providers <-->|4. Relays Queries| SC[Smart Contracts]
    SC <-->|5. Runs on| EVM[Ethereum Virtual Machine / Execution Layer]
    EVM <-->|6. Commits State| BC[(Blockchain Ledger)]
    
    style FE fill:#f9f,stroke:#333,stroke-width:2px
    style Signer fill:#ffcccb,stroke:#333,stroke-width:2px
    style Providers fill:#ccffcc,stroke:#333,stroke-width:2px
    style BC fill:#ffffcc,stroke:#333,stroke-width:2px