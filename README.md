# 🏥 RandevuPlus — Hastane Randevu Yönetim Sistemi

Java ile geliştirilmiş, GUI destekli bir hastane randevu yönetim sistemidir. Hasta, Doktor ve Admin olmak üzere üç farklı kullanıcı rolü ile çalışır; veriler JSON dosyalarında kalıcı olarak saklanır.

---

## 🛠️ Teknoloji & Gereksinimler

| Bileşen | Versiyon |
|---------|----------|
| **Java** | **Java SE 21** |
| GUI | Java Swing |
| JSON Kütüphanesi | Google Gson |
| Veri Saklama | JSON dosyaları (dosya sistemi) |

> ⚠️ Proje **Java SE 21** ile derlenmiş ve test edilmiştir. Farklı versiyonlarda çalışması garanti edilmez.

---

## 📁 Proje Yapısı

OOP-Project/
├── src/
│   └── HR/
│       ├── Main.java               # Giriş noktası
│       ├── Person.java             # Soyut temel sınıf
│       ├── Patient.java            # Hasta sınıfı
│       ├── Doctor.java             # Doktor sınıfı
│       ├── Admin.java              # Admin sınıfı
│       ├── Appointment.java        # Randevu modeli
│       ├── AppointmentException.java # Özel exception sınıfı
│       ├── UserFactory.java        # Factory Design Pattern
│       ├── VeriMerkezi.java        # Singleton veri yöneticisi
│       ├── IReportable.java        # Raporlama arayüzü
│       ├── LoginGUI.java           # Giriş ekranı
│       ├── PatientGUI.java         # Hasta paneli
│       ├── DoctorGUI.java          # Doktor paneli
│       └── AdminGUI.java           # Admin paneli
├── hastalar.json
├── doktorlar.json
├── adminler.json
└── randevular.json

---

## 🎯 Özellikler

### 👤 Hasta
- TC ve şifre ile giriş yapabilir
- Doktor listesini görüntüleyebilir
- Uygun saate randevu alabilir
- Mevcut randevularını görüntüleyebilir ve iptal edebilir

### 👨‍⚕️ Doktor
- TC ve şifre ile giriş yapabilir
- Günlük randevu listesini görüntüleyebilir
- Boş randevu slotlarını görebilir

### 🔧 Admin
- TC ve şifre ile giriş yapabilir
- Yeni doktor ve hasta ekleyebilir / silebilir
- Tüm aktif randevuları listeleyebilir

---

## 🧱 Kullanılan OOP Kavramları & Tasarım Desenleri

| Kavram / Desen | Açıklama |
|----------------|----------|
| **Kalıtım (Inheritance)** | `Patient`, `Doctor`, `Admin` → `Person` soyut sınıfından türer |
| **Soyut Sınıf** | `Person` soyut `login()` metodu içerir |
| **Arayüz (Interface)** | `IReportable` → `showUserInfo()` metodunu zorunlu kılar |
| **Singleton** | `VeriMerkezi` — tek bir örnek üzerinden veri yönetimi |
| **Factory** | `UserFactory` — kullanıcı tipine göre nesne üretir |
| **Exception Handling** | `AppointmentException` — randevu çakışmalarını yakalar |
| **Encapsulation** | Tüm alanlara `private` erişim, getter/setter ile kontrol |
| **Polimorfizm** | `Person` referansıyla farklı kullanıcı tipleri işlenir |

---

## 🗄️ Veri Saklama

Uygulama verilerini JSON formatında yerel dosyalarda saklar:

| Dosya | İçerik |
|-------|--------|
| `hastalar.json` | Kayıtlı hasta bilgileri ve randevu listeleri |
| `doktorlar.json` | Doktor bilgileri ve branş/randevu verileri |
| `adminler.json` | Admin kullanıcı bilgileri |
| `randevular.json` | Tüm randevu kayıtları |

---

## 🔑 Varsayılan Test Hesapları

| Rol | TC | Şifre |
|-----|----|-------|
| Admin | `88888888888` | `admin123` |
| Doktor | `11111111111` | `1234` |
| Hasta | `99999999991` | `1234` |

---

## ▶️ Çalıştırma

1. Projeyi klonlayın veya ZIP olarak indirin.
2. Gson kütüphanesini (`gson-x.x.x.jar`) projenin classpath'ine ekleyin.
3. `Main.java` dosyasını **Java SE 21** ile derleyin ve çalıştırın.
4. JSON dosyaları proje kök dizininde otomatik olarak oluşturulur.

```bash
javac -cp .;gson.jar src/HR/*.java
java  -cp .;gson.jar HR.Main
```

---

## 👥 Geliştirici

Tunahan Özcan
Zeliha Tuğçe Kalaylı 
Hasip Efe Karadağ
Muhammet Emirhan Tuncay
Bu proje, Gazi Üniversitesi Bilgisayar Mühendisliği bölümü **CENG106 — Nesne Yönelimli Programlama** dersi kapsamında geliştirilmiştir.
