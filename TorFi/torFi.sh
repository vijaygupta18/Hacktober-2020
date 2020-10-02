/#!/bin/sh
clear
BLACK='\e[30m'
RED='\e[31m'
GREEN='\e[32m'
YELLOW='\e[33m'
BLUE='\e[34m'
PURPLE='\e[35m'
CYAN='\e[36m'
WHITE='\e[37m'
NC='\e[0m'
echo ""
echo -e """${GREEN} ______   ______     ______     ______   __    
/\__  _\ /\  __ \   /\  == \   /\  ___\ /\ \   
\/_/\ \/ \ \ \/\ \  \ \  __<   \ \  __\ \ \ \  
   \ \_\  \ \_____\  \ \_\ \_\  \ \_\    \ \_\ 
    \/_/   \/_____/   \/_/ /_/   \/_/     \/_/ 
${NC}"""
echo -e "${RED}This Tool Must Run As ROOT | https://thehackingsage.github.io${NC}"
echo ""
echo "---------------------------------------------------------------------"
echo ""
echo -e "${CYAN}This Script Will Change Your System's Network Configurations Files..${NC}"
echo ""
read -r -p "Do You Want To Continue ??? [Y/N] : " check
case "$check" in
[nN][oO]|[nN])
echo ""
echo "Thank You For Using This Tool..!!!"
echo ""
echo -e "Visit ${RED}https://github.com/thehackingsage${NC} for More..!!!"
echo ""
exit 1
;;
*)
echo ""
read -p "Do You Want To Update Your System (Highly Recommended) (Y/N)?" ans
if [ $ans = "y" ] || [ $ans = "Y" ]
then
echo "Updating Package Index.."
sudo apt-get update -y
echo "Updating Old Packages.."
sudo apt-get upgrade -y
fi
echo ""
echo "Downloading and Installing Necessary Packages.."
sudo apt install hostapd dnsmasq tor -y
echo ""
echo "Checking..."
if [ ! -f /etc/tor/torrc ]
then
sudo apt update --fix-missing
sudo apt install hostapd dnsmasq tor -y
fi
echo ""
echo "Configuration Start..."
echo ""
read -p "Enter your PIFI SSID : " torfiname
read -p "Enter Password (8 or More Character) : " torfipass
if [ ! $torfiname ]
then
torfiname="TorFi"
echo ""
echo "SSID Can't Be Blank, Now Your SSID is :" $torfiname
fi
if [ ${#torfipass} -lt 8 ]
then
torfipass="hacktronian"
echo ""
echo "Your Password Length is Short, Now Your WiFi Password is : " $torfipass
fi
sudo cat > hostapd.conf <<EOF
# WiFi Access Point Configuration
interface=wlan0
driver=nl80211
hw_mode=g
channel=6
ieee80211n=1
wmm_enabled=0
macaddr_acl=0
ignore_broadcast_ssid=0
auth_algs=1
wpa=2
wpa_key_mgmt=WPA-PSK
wpa_pairwise=TKIP
rsn_pairwise=CCMP
ssid=$torfiname
wpa_passphrase=$torfipass
EOF
sudo systemctl stop hostapd
sudo systemctl stop dnsmasq
sudo mv hostapd.conf /etc/hostapd/hostapd.conf
repl2="DAEMON_CONF\=\"\/etc\/hostapd\/hostapd\.conf\""
sudo sed -i "/#DAEMON_CONF=\"\"/ s/#DAEMON_CONF=\"\"/$repl2/" /etc/default/hostapd 
repl1="DAEMON_CONF\=\/etc\/hostapd\/hostapd.conf"
sudo sed -i "/$repl1/! s/DAEMON_CONF=/$repl1/" /etc/init.d/hostapd
if [ ! -f /etc/dhcpcd.conf.oldtc ]
then
sudo mv /etc/dhcpcd.conf /etc/dhcpcd.conf.oldtc
else
sudo rm /etc/dhcpcd.conf
fi
sudo cp config/dhcpcd.conf /etc/
sudo systemctl restart dhcpcd
if [ ! -f /etc/dnsmasq.conf.oldtc ]
then
sudo mv /etc/dnsmasq.conf /etc/dnsmasq.conf.oldtc
else
sudo rm /etc/dnsmasq.conf
fi
sudo cp config/dnsmasq.conf /etc/dnsmasq.conf
if [ ! -f /etc/sysctl.conf.oldtc ]
then
sudo cp /etc/sysctl.conf /etc/sysctl.conf.oldtc
fi
repl3="net\.ipv4\.ip_forward=1"
sudo sed -i "/#$repl3/ s/#$repl3/$repl3/" /etc/sysctl.conf
repl="iptables-restore \< \/etc\/iptables\.ipv4\.nat"
sudo sed -i "20 s/exit 0/$repl\nexit 0/" /etc/rc.local
if [ ! -f /etc/tor/torrc.oldtc ]
then
sudo mv /etc/tor/torrc /etc/tor/torrc.oldtc
else
sudo rm /etc/tor/torrc
fi
sudo cat /etc/tor/torrc.oldtc config/torrc.conf >> torrc
sudo mv torrc /etc/tor/torrc
sudo sh -c "echo 1 > /proc/sys/net/ipv4/ip_forward"
sudo iptables -t nat -A POSTROUTING -o eth0 -j MASQUERADE
sudo iptables -F
sudo iptables -t nat -F
sudo iptables -t nat -A PREROUTING -i wlan0 -p tcp --dport 22 -j REDIRECT --to-ports 22
sudo iptables -t nat -A PREROUTING -i wlan0 -p udp --dport 53 -j REDIRECT --to-ports 53
sudo iptables -t nat -A PREROUTING -i wlan0 -p tcp --syn -j REDIRECT --to-ports 9040
sudo sh -c "iptables-save > /etc/iptables.ipv4.nat"
sudo touch /var/log/tor/notices.log
sudo chown debian-tor /var/log/tor/notices.log
sudo chmod 644 /var/log/tor/notices.log
sudo service hostapd start
sudo service dnsmasq start
sudo service tor start
sudo update-rc.d tor enable
echo ""
echo "Configuration is Completed"
echo "Reboot Your System To Start Tor Access Point"
echo ""
read -p "Press Enter to Reboot, CTRL+C to Abort.." chk
sudo reboot
;;
esac
